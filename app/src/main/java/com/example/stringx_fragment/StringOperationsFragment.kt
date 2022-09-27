package com.example.stringx_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import java.io.Serializable

class StringOperationsFragment : Fragment(),View.OnClickListener{

    lateinit var stringOperationsLayout: LinearLayout
    lateinit var resultLayout: LinearLayout

    private lateinit var appendButton: Button
    private lateinit var reverseButton: Button
    private lateinit var splitButton: Button
    private lateinit var uppercaseButton: Button
    private lateinit var viewFunctions: Button

    private lateinit var stringOperations: TextView
    lateinit var outputTextView: TextView
    private lateinit var resultTextView: TextView

    lateinit var out:String

    companion object {
        var isShowResult = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("Tag","onCreate() in 1st fragment")
        parentFragmentManager.setFragmentResultListener("requestKey",this){ result,bundle ->

            isShowResult = true
            arguments = bundle
            outputTextView.text = bundle.getString("result")
            out = outputTextView.text.toString()
            if(activity?.findViewById<View>(R.id.Landscape)!=null) {
                stringOperationsLayout.visibility = View.GONE
                resultLayout.visibility = View.VISIBLE
            }
            Log.v("entry","setFragmentListener $isShowResult and the result is ${bundle.getString("result")}")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("Tag","onCreateView() in 1st fragment")

        return inflater.inflate(R.layout.fragment_string_operations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        Log.v("Tag","onViewCreated() in 1st fragment")

        if(savedInstanceState!=null) {
            out = this.arguments?.getString("result").toString()
        }

        stringOperationsLayout = view.findViewById(R.id.string_operations_layout)
        resultLayout = view.findViewById(R.id.result_layout)

        stringOperations = view.findViewById(R.id.functions_text_view)
        outputTextView = view.findViewById(R.id.output_text_view)
        resultTextView = view.findViewById(R.id.result_text_view)

        appendButton = view.findViewById(R.id.append_Button)
        reverseButton = view.findViewById(R.id.reverse_Button)
        splitButton = view.findViewById(R.id.split_Button)
        uppercaseButton = view.findViewById(R.id.uppercase_Button)
        viewFunctions = view.findViewById(R.id.view_functions_button)

        appendButton.setOnClickListener(this)
        reverseButton.setOnClickListener(this)
        splitButton.setOnClickListener(this)
        uppercaseButton.setOnClickListener(this)
        viewFunctions.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        Log.v("Tag","onClick() in 1st fragment")

        when (v?.id) {
            R.id.append_Button -> {
                toSecondFragment(bundleOf("operation" to StringOperationsModel.APPEND))
            }
            R.id.reverse_Button -> {
                toSecondFragment(bundleOf("operation" to StringOperationsModel.REVERSE))
            }
            R.id.split_Button -> {
                toSecondFragment(bundleOf("operation" to StringOperationsModel.SPLIT))
            }
            R.id.uppercase_Button -> {
                toSecondFragment(bundleOf("operation" to StringOperationsModel.UPPERCASE))
            }
            R.id.view_functions_button -> {
                isShowResult = false
                outputTextView.text = ""
                out = ""
                stringOperationsLayout.visibility = View.VISIBLE
                resultLayout.visibility = View.GONE
            }
        }
    }

    fun toSecondFragment(operationBundle:Bundle){
        val inputReceiverFragment = InputReceiverFragment()
        inputReceiverFragment.arguments = operationBundle
        if(activity?.findViewById<View>(R.id.Portrait)!=null) {
            parentFragmentManager.commit {
                replace(R.id.fragcontainer, inputReceiverFragment).addToBackStack("input")
            }
        }else{
            parentFragmentManager.commit{
                replace(R.id.container2,inputReceiverFragment).addToBackStack("input")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(isShowResult){
            outputTextView.text = out
            stringOperationsLayout.visibility = View.GONE
            resultLayout.visibility = View.VISIBLE
        }
        Log.v("Tag","onResume() in 1st fragment $isShowResult")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.v("Tag", "onSaveInstanceState() in 1st fragment")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.v("Tag","onViewStateRestored() in 1st fragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v("Tag","onDetach() function in 1st fragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.v("Tag","onAttach() function in 1st fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v("Tag","onDestroyView() function in 1st fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Tag","onDestroy() function in 1st fragment")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Tag","onPause() function in 1st fragment")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Tag","onStart() function in 1st fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.v("Tag","onStop() function in 1st fragment")
    }

}

//fun setCallBackInterface(mcallBackInterface: CallBackInterface){
//    callbackinterface = mcallBackInterface
//}