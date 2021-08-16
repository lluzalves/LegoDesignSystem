package com.legods.component.button

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.legods.component.button.databinding.ActivityButtonCatalogBinding

class LegoButtonViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityButtonCatalogBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_button_catalog
        )
    }
}