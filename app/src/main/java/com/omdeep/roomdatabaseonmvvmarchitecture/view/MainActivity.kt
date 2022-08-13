package com.omdeep.roomdatabaseonmvvmarchitecture.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdeep.roomdatabaseonmvvmarchitecture.R
import com.omdeep.roomdatabaseonmvvmarchitecture.adapter.RecyclerViewAdapter
import com.omdeep.roomdatabaseonmvvmarchitecture.databinding.ActivityMainBinding
import com.omdeep.roomdatabaseonmvvmarchitecture.databinding.InsertDataBinding
import com.omdeep.roomdatabaseonmvvmarchitecture.databinding.UpdateDeleteDataBinding
import com.omdeep.roomdatabaseonmvvmarchitecture.factory.DeveloperFactory
import com.omdeep.roomdatabaseonmvvmarchitecture.repository.DeveloperRepository
import com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase.DeveloperDatabase
import com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase.DeveloperEntity
import com.omdeep.roomdatabaseonmvvmarchitecture.viewModel.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //TODO: Initializing all the MVVM concepts used here
        val dao = DeveloperDatabase.getInstance(this).dao
        val repository = DeveloperRepository(dao)
        val factory = DeveloperFactory(repository, application)
        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]

        //TODO: Displaying the developer data
        displayDeveloperList()
        
        binding.floatingBtn.setOnClickListener {
            //TODO: New way of creating dialog box in Kotlin language
            val dialog = Dialog(this)
            val insertDataBinding : InsertDataBinding = DataBindingUtil.inflate(dialog.layoutInflater, R.layout.insert_data, null, false)
            dialog.setContentView(insertDataBinding.root)
            dialog.show()
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            insertDataBinding.cancelBtn.setOnClickListener {
                dialog.dismiss()
            }

            insertDataBinding.insertViewModel = myViewModel

            insertDataBinding.lifecycleOwner = this

        }
    }
    private fun displayDeveloperList() {
        myViewModel.developerList.observe(this, Observer {
            binding.recyclerView.adapter = RecyclerViewAdapter(it) {
                selectedItem : DeveloperEntity ->
                onItemClickListener(selectedItem)
            }
        })
    }
    private fun onItemClickListener(developerEntity: DeveloperEntity) {
        val dialog = Dialog(this)
        val updateDeleteDataBinding : UpdateDeleteDataBinding = DataBindingUtil.inflate(dialog.layoutInflater, R.layout.update_delete_data, null, false)
        dialog.setContentView(updateDeleteDataBinding.root)
        dialog.show()
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        updateDeleteDataBinding.cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        updateDeleteDataBinding.updateViewModel = myViewModel
        updateDeleteDataBinding.lifecycleOwner = this

        myViewModel.getDeveloperData(developerEntity)
    }
}