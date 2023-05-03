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

    private lateinit var navigationView: NavigationView
    private lateinit var mActionBarDrawerToggle: ActionBarDrawerToggle

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        //设置菜单
        doMenu()
        //设置抽屉菜单
        doDraw()
    }


    private fun doDraw() {
        //获得NavHostFragment
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.know_navigation) as NavHostFragment
        //获得容器
        val navController = host.navController
        navigationView = mBinding.nvMenu
         //将navigation里的东西填充到navController里
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
        //放置标题栏
        setSupportActionBar(mBinding.toolbar)
        //设置左上角的小东西可见
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //填充菜单
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.day -> {       //设置日间模式
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                //设置颜色
                mBinding.toolbar.setBackgroundResource(R.color.color_90)
            }
            R.id.night -> {
                //设置标题栏颜色
                mBinding.toolbar.setBackgroundResource(R.color.color_black_90)
                //设置夜间模式
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            }


        }
        return true
    }
}