package com.yujun.weatherapp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yujun.weatherapp.fragment.WeatherHomeFragment
import com.yujun.weatherapp.fragment.WeatherListFragment

// viewPager2와 함께 사용되어 화면을 스와이프하거나 탭을 클릭할 때 서로 다른 Fragment를 표시하는 역할
class WeatherFragmentStateAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2 // viewPager2가 몇 개의 fragment를 표시할지 설정

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WeatherHomeFragment()  // 특정 위치에 나타낼 fragment를 반환
            else -> WeatherListFragment()
        }
    }
}