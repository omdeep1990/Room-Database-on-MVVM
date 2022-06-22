package com.omdeep.roomdatabaseonmvvmarchitecture.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omdeep.roomdatabaseonmvvmarchitecture.databinding.DisplayDataBinding
import com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase.DeveloperEntity

class RecyclerViewAdapter(private val developerList : List<DeveloperEntity>, private var selectedItem: (DeveloperEntity) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DisplayDataBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(developerList[position], selectedItem)
    }

    override fun getItemCount(): Int {
        return developerList.size
    }

    inner class MyViewHolder(private val binding : DisplayDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(developerEntity: DeveloperEntity, selectedItem: (DeveloperEntity) -> Unit) {
            binding.yourName.text = developerEntity.fName+" "+developerEntity.lName
            binding.profession.text = developerEntity.prof
            binding.mobileNumber.text = developerEntity.mobNo
            binding.rootItem.setOnClickListener {
                selectedItem(developerEntity)
            }
        }
    }
}