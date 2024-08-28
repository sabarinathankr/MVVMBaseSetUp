package com.mvvmbasesetup.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mvvmbasesetup.R
import com.mvvmbasesetup.base.BaseActivity
import com.mvvmbasesetup.viewmodel.TestViewModel

class MainActivity : BaseActivity() {
    private val viewModel: TestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        callTestApi()
    }

    private fun testSuccessResponse(result: Result<String>?) {
        val data = result.toString()
    }

     fun callTestApi(){
         viewModel.test().observe(
             this,
             this::testSuccessResponse
         )
     }
}