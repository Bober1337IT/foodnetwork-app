package com.example.foodnetwork.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.foodnetwork.R

data class Food(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)

val food = listOf(
    Food(R.drawable.spaghetti, R.string.food_name_1, R.string.food_description_1),
    Food(R.drawable.ossobuco, R.string.food_name_2, R.string.food_description_2),
    Food(R.drawable.risotto, R.string.food_name_3, R.string.food_description_3),
    Food(R.drawable.panzanella, R.string.food_name_4, R.string.food_description_4),
    Food(R.drawable.saltimbocca, R.string.food_name_5, R.string.food_description_5),
    Food(R.drawable.ravioli, R.string.food_name_6, R.string.food_description_6),
    Food(R.drawable.arancini, R.string.food_name_7, R.string.food_description_7),
    Food(R.drawable.zuppa, R.string.food_name_8, R.string.food_description_8),
    Food(R.drawable.tiramisu, R.string.food_name_9, R.string.food_description_9),
    Food(R.drawable.focaccia, R.string.food_name_10, R.string.food_description_10),
)