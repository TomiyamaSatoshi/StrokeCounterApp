package jp.co.myapplication

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.counter_layout.view.*
import kotlinx.android.synthetic.main.end_counter_layout.view.*

class TotalScoreFragment constructor(_viewPager: ViewPager) : Fragment() {

    val TAG = "TotalScoreFragment"

    var viewPager : ViewPager = _viewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView")

        //レイアウトの取得
        val layout = inflater.inflate(R.layout.end_counter_layout, container, false)

        //プリファレンスから保存データ取得
        val sharedData = activity?.getSharedPreferences("counter", Context.MODE_PRIVATE)
        //トータルカウント用変数初期化
        var totalScore = 0
        var totalPad = 0
        //18ホール分を集計する
        for(i in 0..17){
            //スコアの取得
            val score = sharedData?.getString("score$i", null)
            if(score != null) totalScore += Integer.parseInt(score)

            //パッドの取得
            val pad = sharedData?.getString("pad$i", null)
            if(pad != null) totalPad += Integer.parseInt(pad)
        }

        //集計したトータルを表示
        layout.total_score.text = totalScore.toString()
        layout.total_pad.text = totalPad.toString()

        //クリックリスナー設定
        layout.end_btn.setOnClickListener(View.OnClickListener {
            //ダイアログで終了確認する
            AlertDialog.Builder(activity)
                .setMessage("現在のデータをクリアします。終了しますか？")
                .setPositiveButton("はい") { dialog, which ->
                    //保存データを削除
                    sharedData?.edit()?.clear()?.apply()
                    //ラウンド1に遷移
                    viewPager.currentItem = 0
                }
                .setNegativeButton("いいえ") { dialog, which -> }.show()
        })

        return layout
    }
}