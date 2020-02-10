package com.skcc.wdp.poc.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.NavAction
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skcc.wdp.poc.R
import com.skcc.wdp.poc.utils.Utils
import kotlinx.android.synthetic.main.fragment_pincode.*


class PincodeFragment : BaseFragment() {

    /**
     * 키패드를 관리하기위한 클래스로 selset가 기본값 true를 가지며 false일 경우 클릭이벤트를 주지 않는다
     *
     * @param value     키패드에 나타낼 값
     * @param select    해당 키배프 클릭 여부
     */
    class PinNumber(
        var value: String = "",
        var select: Boolean = true,
        var delete: Boolean = false
    )

    /**
     * 현재까지 작상된 pincode 촤대 숫자 나타내는 내는 변수
     */
    private val PINCODE_MAXCOUNT = 6

    /**
     * 번호 패드 배열
     *
     * 추후 번호의 위치 변경이 필요할 경우 리스트를 섞어서 변형가능
     */
    private var numberList = listOf(
        PinNumber("1"),
        PinNumber("2"),
        PinNumber("3"),
        PinNumber("4"),
        PinNumber("5"),
        PinNumber("6"),
        PinNumber("7"),
        PinNumber("8"),
        PinNumber("9"),
        PinNumber(select = false),
        PinNumber("0"),
        PinNumber(select = true, delete = true)
    )

    /**
     * 상단 pin code 를 나타내기위한 ImageView 배열
     */
    private var pincodeViewList = arrayListOf<ImageView>()

    private var pincodeList = arrayListOf<String>()

    override fun getLayoutResID(): Int = R.layout.fragment_pincode

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pincodeViewList.apply {
            add(v_pin_1.findViewById(R.id.iv_pin))
            add(v_pin_2.findViewById(R.id.iv_pin))
            add(v_pin_3.findViewById(R.id.iv_pin))
            add(v_pin_4.findViewById(R.id.iv_pin))
            add(v_pin_5.findViewById(R.id.iv_pin))
            add(v_pin_6.findViewById(R.id.iv_pin))
        }


        // RecyclerView에 PinNumberAdapter를 적용
        rv_keypad.adapter = PinNumberAdapter(
            context!!,
            numberList,
            View.OnClickListener {
                var position = it.tag as Int

                when (position) {
                    9 -> {
                    }
                    11 -> {
                        setPinText(remove = true)
                    }
                    10 -> {
                        setPinText("0")
                    }
                    else -> {
                        setPinText("${++position}")
                    }
                }

            })

        activity?.run {
            //RecyclerView의 layoutManager를 GridLayoutManager로 지정하여 gridview 형태로 사용 하도록함 spanCount로 한 행에 표현할 데이터 숫자를 지정
            rv_keypad.layoutManager = GridLayoutManager(this, 3)
            //상단 타이틀바 컬려 변경을 위해 사용
            Utils.setStatusBarColor(this, true, "#ffffff")
        }
    }

    /**
     * 키패드의 클릭 이벤트를 통해 상단의 pincode TextView에 데이터를 출력
     *
     * @param position  데이터를 출력할 TextView 위치
     * @param value     출력할 데이터
     */
    private fun setPinText(value: String = "", remove: Boolean = false) {
        var position = pincodeList.size
        if (position < PINCODE_MAXCOUNT || remove)
            if (remove) {
                if (--position >= 0) {
                    pincodeViewList[position].apply {
                        setImageResource(R.drawable.ic_oval_disable)
                    }
                    pincodeList.removeAt(position)
                }
            } else {
                pincodeViewList[pincodeList.size].apply {
                    setImageResource(R.drawable.ic_oval_enable)
                }
                pincodeList.add(value)
            }

        if (pincodeList.size == PINCODE_MAXCOUNT) {
            checkPinCode()
            findNavController().navigate(R.id.action_pincodeFragment_to_certificationFragment);
        }
    }

    /**
     * pincode가 모두 입력되면 호출 함
     */
    private fun checkPinCode() {
        var pin = StringBuffer()
        for (i in 0 until PINCODE_MAXCOUNT) {
            pin.append(pincodeList[i])
        }
        Log.i("sgim", "pin $pin")
    }

    /**
     * 키패드를 표현하기 위한 RecyclerView Adapter로 기본적으로 ViewHolder 패턴을 가지고 있음
     *
     * @param context
     * @param list      RecyclerView에 표현할 데이터 배열
     * @param listener  클릭 이벤트 리스너
     */
    private class PinNumberAdapter(
        var context: Context,
        var list: List<PinNumber>,
        var listener: View.OnClickListener
    ) :
        RecyclerView.Adapter<PinNumberAdapter.NumberViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
            var view =
                LayoutInflater.from(context).inflate(R.layout.view_number, parent, false)

            // parent에 post하지 않고 바로 measuredHeight를 하면 해당 사이즈가 0으로 호출됨 post를 통해 뷰가 생성된 후 에 실행하여 해당 사이즈를 받아오도록 함
            parent.post {
                val height = parent.measuredHeight / 4
                view.minimumHeight = height
            }
            return NumberViewHolder(
                view
            )
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
            list[position].run {
                holder.tvNumber.text =
                    String.format(context.getString(R.string.login_number), value)
                // 해당 뷰에 position을 tag값으로 할당하여 해당 뷰가 클릭될때 tag값을 확인 어떤 데이터가 클릭되었는지 판단하기 위해 사용
                holder.viewNumber.tag = position
                // select 여부를 통해 클릭 이벤트를 할당 함
                if (select) {
                    holder.viewNumber.setOnClickListener(listener)
                }

                if (delete) {
                    holder.ivDelete.visibility = View.VISIBLE
                }
            }
        }

        class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var viewNumber: FrameLayout = itemView.findViewById(R.id.view_number)
            var tvNumber: TextView = itemView.findViewById(R.id.tv_number)
            var ivDelete: AppCompatImageView = itemView.findViewById(R.id.iv_delete)
        }
    }
}