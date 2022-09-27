package com.example.stringx_fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("tag", "onCreate()")

        if (savedInstanceState == null) {

            supportFragmentManager.commit {
                replace(R.id.fragcontainer, StringOperationsFragment(), "first")
            }
        } else {
            when {
                findViewById<View>(R.id.Portrait) != null -> {
                    val fragmentFromContainer = supportFragmentManager.findFragmentById(R.id.container2)
                    val savedFrag = fragmentFromContainer?.let {
                        supportFragmentManager.saveFragmentInstanceState(it)
                    }
                    val newInstance = fragmentFromContainer?.javaClass?.newInstance()

                    if (newInstance !is StringOperationsFragment) {

                        newInstance?.arguments = fragmentFromContainer?.arguments
                        newInstance?.setInitialSavedState(savedFrag)

                        supportFragmentManager.commit {
                            if (newInstance != null) {
                                replace(R.id.fragcontainer, newInstance).addToBackStack("")
                            }
                        }
                    }
                }
                (findViewById<View>(R.id.Landscape) != null) -> {
                    val fragmentFromContainer = supportFragmentManager.findFragmentById(R.id.fragcontainer)
                    val savedFragment = fragmentFromContainer?.let {
                        supportFragmentManager.saveFragmentInstanceState(it)
                    }
                    val newInstance = fragmentFromContainer?.javaClass?.newInstance()

                    if (newInstance !is StringOperationsFragment) {

                        onBackPressed()

                        newInstance?.arguments = fragmentFromContainer?.arguments
                        newInstance?.setInitialSavedState(savedFragment)
                        supportFragmentManager.commit {
                            if (newInstance != null) {
                                replace(R.id.container2, newInstance).addToBackStack("input")
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount>0){
            for(i in 1..supportFragmentManager.backStackEntryCount)
            supportFragmentManager.popBackStack()
        }
        else if(StringOperationsFragment.isShowResult){
            val stringOperationsFragment = supportFragmentManager.findFragmentById(R.id.fragcontainer) as StringOperationsFragment

            stringOperationsFragment.stringOperationsLayout.visibility = View.VISIBLE
            stringOperationsFragment.resultLayout.visibility = View.GONE

            StringOperationsFragment.isShowResult = false
        }
        else
            super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Tag","onDestroy() function in Activity")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Tag","onPause() function in Activity")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Tag","onStart() function in Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.v("Tag","onStop() function in Activity")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.v("Tag","onSaveInstanceState() function in Activity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("Tag","onRestart() of Activity")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.v("Tag","onRestoreInstanceState() of Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Tag","onResume() function in Activity")
    }

}

//
//    override fun callBackMethod(operationBundle: Bundle) {
//        isInput = true
//
//        inputReceiverFragment.arguments = operationBundle
//        if(findViewById<View>(R.id.Portrait)!=null){
//            supportFragmentManager.commit {
//                replace(R.id.fragcontainer, inputReceiverFragment, "InputReceiverFragment")
//            }
//        } else{
//            supportFragmentManager.commit {
//            replace(R.id.container2, inputReceiverFragment, "InputReceiverFragment")
//        }
//    }
//    }
//                    stringOperationsFragment = supportFragmentManager.findFragmentByTag("first") as StringOperationsFragment
//                    Log.v("hash","second hashcode${stringOperationsFragment.hashCode()}")
//                        supportFragmentManager.commit {
//                            replace(R.id.fragcontainer,StringOperationsFragment()).addToBackStack("")
//                        }
//        inputReceiverFragment = savedInstanceState.getSerializable("inputReceiver") as InputReceiverFragment
//        stringOperationsFragment = savedInstanceState.getSerializable("stringObj") as StringOperationsFragment
//        if(isInput)
//        supportFragmentManager.putFragment(outState, "input", inputReceiverFragment)
//        if(stringOperationsFragment.isAdded)
//        supportFragmentManager.putFragment(outState, "string", stringOperationsFragment)
//        Log.v("tag","onBackpressed()")
//Log.v("count"," count: ${supportFragmentManager.backStackEntryCount} and result is ${stringOperationsFragment.isShowResult}")
////        when {
//            (StringOperationsFragment.isShowResult) -> {
//                stringOperationsFragment = supportFragmentManager.findFragmentById(R.id.fragcontainer) as StringOperationsFragment
//                stringOperationsFragment.stringOperationsLayout.visibility = View.VISIBLE
//                stringOperationsFragment.resultLayout.visibility = View.GONE
//                StringOperationsFragment.isShowResult = false
//                Log.v("count"," count: ${supportFragmentManager.backStackEntryCount} and result is ${StringOperationsFragment.isShowResult} condition 3")
//
//            }
//            (findViewById<View>(R.id.Portrait)!=null)->{
//                supportFragmentManager.popBackStack("Pinput0",POP_BACK_STACK_INCLUSIVE)
//                StringOperationsFragment.portraitCount = 0
//                Log.v("count"," count: ${supportFragmentManager.backStackEntryCount} and result is ${StringOperationsFragment.isShowResult} condition 1")
//
//            }
//            (findViewById<View>(R.id.Landscape)!=null)-> {
//                supportFragmentManager.popBackStack("Linput0", POP_BACK_STACK_INCLUSIVE)
//                StringOperationsFragment.landscapeCount = 0
//                Log.v("count"," count: ${supportFragmentManager.backStackEntryCount} and result is ${StringOperationsFragment.isShowResult} condition 2")
//
//            }
//            else -> super.onBackPressed()
//        }


//        if(savedInstanceState!=null){
//               val mstringOperationsFragment = supportFragmentManager.getFragment(savedInstanceState, "string")
//               val  minputReceiverFragment = supportFragmentManager.getFragment(savedInstanceState,"input")
//            if(minputReceiverFragment!=null)
//                inputReceiverFragment = minputReceiverFragment as InputReceiverFragment
//            if(mstringOperationsFragment!=null)
//                stringOperationsFragment = mstringOperationsFragment as StringOperationsFragment
//        }
//        else{
//            stringOperationsFragment = StringOperationsFragment()
//            inputReceiverFragment = InputReceiverFragment()
//        }
//        if(findViewById<View>(R.id.Portrait)!=null) {
//            if (savedInstanceState == null) {
//                stringOperationsFragment.setCallBackInterface(this)
//                supportFragmentManager.commit {
//                    replace(R.id.fragcontainer, stringOperationsFragment).addToBackStack("StringOperationsFragment")
//                }
//            } else {
//                if (supportFragmentManager.findFragmentById(R.id.container2) is InputReceiverFragment){
////                    inputReceiverFragment = supportFragmentManager.findFragmentByTag("InputReceiverFragment") as InputReceiverFragment
////                    val inp = InputReceiverFragment()
////                    inp.arguments = inputReceiverFragment.arguments
//                    isInput = true
//
//                    supportFragmentManager.commit {
//                        replace(R.id.fragcontainer, inputReceiverFragment).addToBackStack("InputReceiverFragment")
//                    }
//                }
//            }
//        }
//        if(findViewById<View>(R.id.Landscape)!=null){
//            if(savedInstanceState  == null){
//                stringOperationsFragment.setCallBackInterface(this)
//                supportFragmentManager.commit {
//                    replace(R.id.fragcontainer, stringOperationsFragment).addToBackStack("StringOperationsFragment")
//                }
//            }else{
//                if(supportFragmentManager.findFragmentById(R.id.fragcontainer) is InputReceiverFragment){
//                    Log.v("Tag"," is input receiver ${supportFragmentManager.findFragmentById(R.id.fragcontainer) is InputReceiverFragment} ${supportFragmentManager.findFragmentById(R.id.fragcontainer)}")
////                    inputReceiverFragment = supportFragmentManager.findFragmentByTag("InputReceiverFragment") as InputReceiverFragment
////                    stringOperationsFragment = supportFragmentManager.findFragmentByTag("StringOperationsFragment") as StringOperationsFragment
////                    val inp = InputReceiverFragment()
//
////                    inp.arguments = inputReceiverFragment.arguments
//                    isInput = true
//                    supportFragmentManager.commit {
//                        replace(R.id.container2, inputReceiverFragment).addToBackStack("InputReceiverFragment")
//                    }
//                    supportFragmentManager.commit {
//                        replace(R.id.fragcontainer,stringOperationsFragment).addToBackStack("StringOperationsFragment")
//                    }
//                }
//            }
//        }
//        Log.v("sarvesh"," ${stringOperationsFragment}  \n ${inputReceiverFragment}")

//    override fun onBackPressed() {
//        stringOperationsFragment = supportFragmentManager.findFragmentByTag("StringOperationsFragment") as StringOperationsFragment
//        Log.v("Tag","onBackPressed() function in Activity ${stringOperationsFragment.isShowResult}")
//        if(stringOperationsFragment.isShowResult) {
//            stringOperationsFragment.isShowResult = false
//            stringOperationsFragment.stringOperationsLayout.visibility = View.VISIBLE
//            stringOperationsFragment.resultLayout.visibility = View.GONE
//        }
//        else
//            super.onBackPressed()
//    }

//        val fragmentPortrait = supportFragmentManager.findFragmentById(R.id.container1)
//        val fragmentlandscape = supportFragmentManager.findFragmentById(R.id.container2)
//        if(findViewById<View>(R.id.Portrait)!=null) {
//            supportFragmentManager.popBackStack(0,0)
//        }else if((findViewById<View>(R.id.Landscape)!=null) and (!StringOperationsFragment.isShowResult)){
//            supportFragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        }


//        else if(supportFragmentManager.findFragmentById(R.id.fragcontainer) is StringOperationsFragment){
//                stringOperationsFragment = supportFragmentManager.findFragmentById(R.id.fragcontainer) as StringOperationsFragment
//                if(stringOperationsFragment.isShowResult) {
//                    stringOperationsFragment =
//                        supportFragmentManager.findFragmentById(R.id.fragcontainer) as StringOperationsFragment
//                    Log.v("pr","onBack ${stringOperationsFragment.isShowResult}")
//                    stringOperationsFragment.stringOperationsLayout.visibility = View.VISIBLE
//                    stringOperationsFragment.resultLayout.visibility = View.GONE
//                    stringOperationsFragment.isShowResult = false
//                }
//        }