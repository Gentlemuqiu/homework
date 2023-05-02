package com.example.midtest

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.midtest.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var navigationView: NavigationView
    lateinit var mActionBarDrawerToggle: ActionBarDrawerToggle

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        doMenu()
        doDraw()
    }


    private fun doDraw() {
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.know_navigation) as NavHostFragment
        val navController = host.navController
        navigationView = mBinding.nvMenu
        navigationView.setupWithNavController(navController)

        //将toolbar和draw关联起来
        mActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            mBinding.dlLeft,
            mBinding.toolbar,
            0,
            0
        )
        //可以监听到打开和关闭
        mBinding.dlLeft.addDrawerListener(mActionBarDrawerToggle)
        //同步一下
        mActionBarDrawerToggle.syncState()
    }

    private fun doMenu() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.day -> {       //设置日间模式
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                mBinding.toolbar.setBackgroundResource(com.google.android.material.R.color.material_dynamic_primary90)
            }
            R.id.night -> {//设置夜间模式
                mBinding.toolbar.setBackgroundResource(com.bumptech.glide.R.color.material_blue_grey_800)
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            }


        }
        return true
    }


    /* override fun onAttachedToWindow() {
         super.onAttachedToWindow()
         handler.post(mLooperTask)
     }

     override fun onDetachedFromWindow() {
         super.onDetachedFromWindow()
         //取消循环
         handler.removeCallbacks(mLooperTask)
     }*/

}