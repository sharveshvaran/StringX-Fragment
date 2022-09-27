package com.example.stringx_fragment

import android.app.ActionBar
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import java.io.Serializable

class InputReceiverFragment : Fragment(){

    private lateinit var input1EditText: EditText
    private lateinit var input2EditText: EditText

    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("Tag","onCreate() in 2nd fragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v("Tag","onCreateView() in 2nd fragment")
        return inflater.inflate(R.layout.fragment_input_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        input1EditText = view.findViewById(R.id.input1_editText)
        input2EditText = view.findViewById(R.id.input2_editText)

        submitButton = view.findViewById(R.id.submit_button)

        Log.v("Tag","onViewCreated() in 2nd fragment")

        val option = this.arguments?.get("operation") as StringOperationsModel

        when(option){
            StringOperationsModel.APPEND -> {
                submitButton.setOnClickListener {
                    val input1 = input1EditText.text.toString()
                    val input2 = input2EditText.text.toString()

                    if (input1.isEmpty() or input2.isEmpty()) {
                        Toast.makeText(activity, "fill all the fields!", Toast.LENGTH_SHORT).show()
                    } else {
                        val result = input1 + input2
                        activity?.onBackPressed()
                        parentFragmentManager.setFragmentResult("requestKey", bundleOf("result" to result))
                    }
                }
            }
            StringOperationsModel.REVERSE -> {
                input2EditText.visibility = View.GONE
                input1EditText.hint = "Enter Text to Reverse"

                submitButton.setOnClickListener {

                    val input1 = input1EditText.text.toString()

                    if (input1.isEmpty()) {
                        Toast.makeText(context, "fill all the fields!", Toast.LENGTH_SHORT).show()
                    } else {
                        val result = input1.reversed()
                        parentFragmentManager.setFragmentResult("requestKey", bundleOf("result" to result))
                        activity?.onBackPressed()

                    }
                }
            }
            StringOperationsModel.SPLIT -> {
                input2EditText.hint = "delimeter"
                submitButton.setOnClickListener {

                    val input1 = input1EditText.text.toString()
                    val input2 = input2EditText.text.toString()
                    var result:String

                    when {
                        input1.isEmpty() and input2.isEmpty() -> Toast.makeText(context, "fill all the fields!", Toast.LENGTH_SHORT).show()

                        input2.isEmpty() -> {
                            result = input1.split("").toString()
                            activity?.onBackPressed()
                            parentFragmentManager.setFragmentResult("requestKey", bundleOf("result" to result))
                        }

                        input2.length > 1 -> Toast.makeText(context, "delimeter must be a single chaacter", Toast.LENGTH_SHORT).show()

                        else -> {
                            result = input1.split(input2).toString()
                            activity?.onBackPressed()
                            parentFragmentManager.setFragmentResult("requestKey", bundleOf("result" to result))
                        }

                    }
                }
            }
            StringOperationsModel.UPPERCASE -> {
                input2EditText.visibility = View.GONE
                input1EditText.hint = "Enter Text to be Uppercased"

                submitButton.setOnClickListener {
                    val input = input1EditText.text.toString()
                    when {
                        input.isEmpty() -> Toast.makeText(context, "fill the input field", Toast.LENGTH_SHORT).show()
                        validUppercaseInput(input) -> {
                            val result = input.uppercase()
                            activity?.onBackPressed()
                            parentFragmentManager.setFragmentResult("requestKey", bundleOf("result" to result))
                        }
                        else -> Toast.makeText(context, "invalid input", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun validUppercaseInput(input:String):Boolean{
        input.forEach {
            if(!it.isLetter())
                return false
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.v("Tag","onSaveInstanceState() in 2nd fragment")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.v("Tag","onViewStateRestored() in 2nd fragment")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Tag","onResume() in 2nd fragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v("Tag","onDetach() function in 2nd fragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.v("Tag","onAttach() function in 2nd fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v("Tag","onDestroyView() function in 2nd fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Tag","onDestroy() function in 2nd fragment")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Tag","onPause() function in 2nd fragment")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Tag","onStart() function in 2nd fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.v("Tag","onStop() function in 2nd fragment")
    }
}