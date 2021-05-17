package com.group19.foodrecipeapp.entities

import androidx.room.*
import com.group19.foodrecipeapp.Converter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(Converter::class)
    var categorieitems: List<CategoryItems>? = null
)
