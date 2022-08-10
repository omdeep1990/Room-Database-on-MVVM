package com.omdeep.roomdatabaseonmvvmarchitecture.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.omdeep.roomdatabaseonmvvmarchitecture.repository.DeveloperRepository
import com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase.DeveloperEntity
import kotlinx.coroutines.launch

class MyViewModel(private val developerRepository: DeveloperRepository, application: Application) : AndroidViewModel(application) {

    var firstName : MutableLiveData<String?> = MutableLiveData("")
    var lastName : MutableLiveData<String?> = MutableLiveData("")
    var profession : MutableLiveData<String?> = MutableLiveData("")
    var mobileNo : MutableLiveData<String?> = MutableLiveData("")
    var upFirstName = MutableLiveData<String?>()
    var upLastName = MutableLiveData<String?>()
    var upProfession = MutableLiveData<String?>()
    var upMobileNo = MutableLiveData<String?>()

    var firstNameText = MutableLiveData<String?>()
    var lastNameText = MutableLiveData<String?>()
    var professionText = MutableLiveData<String?>()
    var mobileNoText = MutableLiveData<String?>()

    var saveBtn = MutableLiveData<String?>()
    var cancelBtn = MutableLiveData<String?>()
    var updateData = MutableLiveData<String?>()
    var deleteData = MutableLiveData<String?>()

    var isUpdateOrDelete = false
    lateinit var developerData : DeveloperEntity

    //TODO: Initializing values to any button or text view at first time
    init {
        saveBtn.value = "SAVE"
        cancelBtn.value = "CANCEL"
        updateData.value = "UPDATE"
        deleteData.value = "DELETE"
        firstNameText.value = "ENTER YOUR FIRST NAME"
        lastNameText.value = "ENTER YOUR LAST NAME"
        professionText.value = "ENTER YOUR PROFESSION"
        mobileNoText.value = "ENTER YOUR MOBILE NO"
    }

    //TODO: Function for inserting data into database
    fun saveDataOnClick() {
        if (validation())
            {
                val fName = firstName.value!!
                val lName = lastName.value!!
                val prof = profession.value!!
                val mobNo = mobileNo.value!!
                insert((DeveloperEntity(0, fName, lName, prof, mobNo)))
                setNullValue()
            }
    }

    private fun validation() : Boolean{
        return when {
            firstName.value!!.isEmpty()-> {
                Toast.makeText(getApplication(), "Enter The First Name!", Toast.LENGTH_SHORT).show()
                false
            }
            lastName.value!!.isEmpty()-> {
                Toast.makeText(getApplication(), "ENter Your Last NAme!", Toast.LENGTH_SHORT).show()
                false
            }
            profession.value!!.isEmpty()-> {
                Toast.makeText(getApplication(), "ENter Your Profession!", Toast.LENGTH_SHORT).show()
                false
            }
            mobileNo.value!!.isEmpty()-> {
                Toast.makeText(getApplication(), "ENter Your Mobile No!", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                return true
            }
        }
    }

    //TODO: Function for updating data into database
    fun updateDataOnClick() {
        if (isUpdateOrDelete) {
            developerData.fName = upFirstName.value!!
            developerData.lName = upLastName.value!!
            developerData.prof = upProfession.value!!
            developerData.mobNo = upMobileNo.value!!

            update(developerData)
            setNullValueForUpdatedData()
        }
    }

    //TODO: Function to set data from database for deleting or updating in Mutable Live Data
    fun getDeveloperData(developerEntity: DeveloperEntity) {
        upFirstName.value = developerEntity.fName
        upLastName.value = developerEntity.lName
        upProfession.value = developerEntity.prof
        upMobileNo.value = developerEntity.mobNo

        isUpdateOrDelete = true
        developerData = developerEntity
    }

    //TODO: Function for deleting a particular data from Room database
    fun deleteDataOnClick() {
        if (isUpdateOrDelete) {
            delete(developerData)
            setNullValueForUpdatedData()
        }
    }

    //TODO: Function for deleting all the data from the database at one time
    private fun deleteAll() {
        clearAll()
    }
    //TODO: All CRUD functions called from Repository to ViewModel
    //TODO: 1. Insert Function
    private fun insert(developerEntity: DeveloperEntity) {
        viewModelScope.launch {
            developerRepository.insert(developerEntity)
        }
    }

    //TODO: 2. Update Function
    private fun update(developerEntity: DeveloperEntity) {
        viewModelScope.launch {
            developerRepository.update(developerEntity)
        }
    }

    //TODO: 3. Delete Function
    private fun delete(developerEntity: DeveloperEntity) {
        viewModelScope.launch {
            developerRepository.delete(developerEntity)
        }
    }

    //TODO: 4. Display Function
    val developerList = developerRepository.developers

    //TODO: 5. Delete All Function
    private fun clearAll() {
        viewModelScope.launch {
            developerRepository.deleteAll()
        }
    }

    //TODO: Function data from layout
    private fun setNullValue() {
        firstName.value = null
        lastName.value = null
        profession.value = null
        mobileNo.value = null
    }

    private fun setNullValueForUpdatedData() {
        upFirstName.value = null
        upLastName.value = null
        upProfession.value = null
        upMobileNo.value = null
    }
}