package com.example.midtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
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
            R.id.day -> Toast.makeText(this, "日间模式", Toast.LENGTH_SHORT).show()
            R.id.night -> Toast.makeText(this, "夜间模式", Toast.LENGTH_SHORT).show()
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