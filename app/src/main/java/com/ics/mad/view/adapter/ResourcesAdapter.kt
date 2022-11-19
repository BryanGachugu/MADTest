package com.ics.mad.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ics.mad.R
import com.ics.mad.databinding.ResourceViewBinding
import com.ics.mad.model.data.CodingResource

class ResourcesAdapter(
    private val list: MutableList<CodingResource>
) : RecyclerView.Adapter<ResourcesAdapter.ResourceViewHolder>() {
    
    lateinit var binding: ResourceViewBinding

    public class ResourceViewHolder(private var resourceViewBinding: ResourceViewBinding) :
        RecyclerView.ViewHolder(resourceViewBinding.root) {
        fun bind(codingResource: CodingResource) {
            resourceViewBinding.resource = codingResource

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResourceViewHolder(
            DataBindingUtil.inflate( LayoutInflater.from(parent.context),
                R.layout.resource_view,
                parent,
                false)
        )



    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val codingResource = list[position]
        holder.bind(codingResource)

    }

    override fun getItemCount() = list.size

}