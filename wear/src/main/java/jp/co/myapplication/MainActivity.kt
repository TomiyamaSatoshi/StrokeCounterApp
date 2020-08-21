package jp.co.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.viewpager_main.*

class MainActivity : FragmentActivity() {

    val TAG = "MainActivity"

    /**
     * 初期処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewpager_main)

        Log.d(TAG, "MainActivity初期処理")

        //フラグメントのリストを作成
        val fragmentList : MutableList<Fragment> = arrayListOf()
        //18ホール分のフラグメントを追加
        for(i in 0..17 ) fragmentList.add(InputScoreFragment(viewPager))
        //更新用にブランクページを追加
        fragmentList.add(BlankFragment())
        //スコアリストページを追加
        fragmentList.add(ScoreListFragment(viewPager))
        //トータルページを追加
        fragmentList.add(TotalScoreFragment(viewPager))

        //adapterのインスタンス生成
        val adapter = CustomPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            //スクロールしたら呼ばれる
            override fun onPageSelected(position: Int) {
                //データリスト表を作らせないためにブランクページを追加している
                //ブランクページに遷移したらデータ表ページに遷移する
                if(position == 18){
                    viewPager.currentItem = 19
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })
    }
}
