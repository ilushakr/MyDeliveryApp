package com.example.iikoapi.general

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.iikoapi.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class BasketFragment(val navigationView: BottomNavigationView) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_basket, container, false)

        var bottomBackToMenu = view.findViewById<TextView>(R.id.empty_basket_text)
        //var navigationView = view.rootView.findViewById<BottomNavigationView>(R.id.navigationView)

        bottomBackToMenu.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_anim_left,R.anim.exit_anim_left)
                .replace(R.id.fragment_container, MenuFragment(0), "1")
                .commit()
            navigationView.menu.getItem(0).isChecked = true
        }

        return view
    }

}