package com.daniel.legodesignsystem.model

import androidx.annotation.DrawableRes

data class ComponentItem(
    val title: String,
    @DrawableRes val icon: Int,
    val sectionToOpen: Component,
)