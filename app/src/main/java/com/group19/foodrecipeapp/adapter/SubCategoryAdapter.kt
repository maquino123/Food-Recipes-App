package com.group19.foodrecipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.group19.foodrecipeapp.R
import com.group19.foodrecipeapp.entities.MealItems
import kotlinx.android.synthetic.main.activity_dish.view.*

class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {

    var listener: SubCategoryAdapter.OnItemClickListener? = null
    var ctx :Context? = null
    var arrSubCategory = ArrayList<MealItems>()
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    fun setData(arrData : List<MealItems>){
        arrSubCategory = arrData as ArrayList<MealItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_dish,parent,false))
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.itemView.tv_dish_name.text = arrSubCategory[position].strMeal

        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.itemView.img_dish)

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrSubCategory[position].idMeal)
        }
    }

    interface OnItemClickListener{
        fun onClicked(id:String)
    }
}