package com.jammali.cryptocurrency.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins_list")
data class CoinsListEntity(

@PrimaryKey val symbol: String,
    val coinId: String?,
    val name: String?,
    val price: Double?,
    val changePercent: Double? = null,
    val image: String? = null,
)