package jp.co.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.counter_list_body_layout.view.*
import kotlinx.android.synthetic.main.counter_list_layout.view.*

class ScoreListFragment constructor(_viewpager: ViewPager) : Fragment() {

    val TAG = "ScoreListFragment"

    var viewpager : ViewPager = _viewpager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView")

        //レイアウトの取得
        val layout = inflater.inflate(R.layout.counter_list_layout, container, false)

        //テーブルレイアウトを作成
        val tableBodyLayout = TableLayout(context)

        //プリファレンスからデータ取得
        val sharedData = activity?.getSharedPreferences("counter", Context.MODE_PRIVATE)

        //18ホール分の入力データ表を作成
        for(i in 0..17){
            //スコアとパッド取得
            val score = sharedData?.getString("score$i", "0")
            val pad = sharedData?.getString("pad$i", "0")

            //テーブルのレイアウト取得
            val bodyLayout = inflater.inflate(R.layout.counter_list_body_layout, container, false)
            //データを表示
            bodyLayout.hole_num.text = (i + 1).toString()
            bodyLayout.score.text = score
            bodyLayout.pad.text = pad
            //クリックイベント設定
            bodyLayout.isClickable = true
            bodyLayout.setOnClickListener(View.OnClickListener {
                //表の行に対応するページに遷移する
                viewpager.currentItem = i
            })
            //テーブル行を親レイアウトに追加
            tableBodyLayout.addView(bodyLayout)
        }

        layout.table_body.addView(tableBodyLayout)
        return layout
    }
}