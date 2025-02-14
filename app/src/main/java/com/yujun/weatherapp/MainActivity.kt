package com.yujun.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yujun.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding   //변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)      // 액티비티가 재생성될 때 저장된 번들 객체로 데이터를 복원
        binding = ActivityMainBinding.inflate(layoutInflater)   // 변수 초기화, 이때 실제 뷰들이 메모리에 로드되고, binding 객체에 연결된다.
        with(binding) {
            setContentView(root)
            val adapter = WeatherFragmentStateAdapter(this@MainActivity)
            viewPager.adapter = adapter
            indicator.setViewPager(viewPager)
            adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        }
    }
}


/*
    WeatherFragmentStateAdapter가 초기화될 때, 프래그먼트를 생성하면서 해당 프래그먼트의 onCreateView 메서드가 자동으로 호출
    이 과정은 ViewPager와 WeatherFragmentStateAdapter의 협력으로 프래그먼트가 동적으로 추가되고 표시되는 방식
*/
