package jp.co.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.counter_layout.*
import kotlinx.android.synthetic.main.counter_layout.view.*

class InputScoreFragment constructor(_viewPager: ViewPager) : Fragment() {

    val TAG = "InputScoreFragment"

    var viewPager : ViewPager = _viewPager
    var pageNum = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

        //レイアウトの取得
        val layout = inflater.inflate(R.layout.counter_layout, container, false)

        //バンドルの取得
        val bundle : Bundle? = arguments
        //ページ番号の取得
        pageNum = bundle?.get("pageNum") as Int
        Log.d(TAG, "ページ番号：$pageNum")

        //プリファレンス取得
        val sharedData = activity?.getSharedPreferences("counter", Context.MODE_PRIVATE)
        //スコアデータ取得
        val scoreNum = sharedData?.getString("score$pageNum", null)
        if(scoreNum != null){
            Log.d(TAG, "スコア：$scoreNum")
            layout.score_num.text = scoreNum
        }
        //パッド情報取得
        val padNum = sharedData?.getString("pad$pageNum", null)
        if(padNum != null){
            Log.d(TAG, "パッド：$padNum")
            layout.pad_num.text = padNum
        }

        //ラウンド数を表示
        val roundTextView = layout.round_num
        roundTextView.text = (pageNum + 1).toString()

        //クリックリスナーを登録
        layout.minus_score.setOnClickListener(View.OnClickListener { clickScoreMinus() })
        layout.plus_score.setOnClickListener(View.OnClickListener { clickScorePlus() })
        layout.minus_pad.setOnClickListener(View.OnClickListener { clickPadMinus() })
        layout.plus_pad.setOnClickListener(View.OnClickListener { clickPadPlus() })
        layout.total_btn.setOnClickListener(View.OnClickListener { viewPager.currentItem = 18 })

        return layout
    }

    /**
     * スコアクリック（ー）
     */
    private fun clickScoreMinus() {
        val nowScoreNumStr = score_num.text
        val nowScoreNum = Integer.parseInt(nowScoreNumStr as String)
        if(nowScoreNum != 0){
            score_num.text = (nowScoreNum - 1).toString()
        }
        saveData("score$pageNum", (nowScoreNum - 1).toString())
    }

    /**
     * スコアクリック（＋）
     */
    private fun clickScorePlus() {
        val nowScoreNumStr = score_num.text
        val nowScoreNum = Integer.parseInt(nowScoreNumStr as String)
        score_num.text = (nowScoreNum + 1).toString()
        saveData("score$pageNum", (nowScoreNum + 1).toString())
    }

    /**
     * パッドクリック（－）
     */
    private fun clickPadMinus() {
        val nowPadNumStr = pad_num.text
        val nowPadNum = Integer.parseInt(nowPadNumStr as String)
        if(nowPadNum != 0){
            pad_num.text = (nowPadNum - 1).toString()
        }
        saveData("pad$pageNum", (nowPadNum - 1).toString())
    }

    /**
     * パッドクリック（＋）
     */
    private fun clickPadPlus() {
        val nowPadNumStr = pad_num.text
        val nowPadNum = Integer.parseInt(nowPadNumStr as String)
        pad_num.text = (nowPadNum + 1).toString()
        saveData("pad$pageNum", (nowPadNum + 1).toString())
    }

    /**
     * 入力データ保存
     */
    private fun saveData(key : String, value : String){
        val sharedData = activity?.getSharedPreferences("counter", Context.MODE_PRIVATE)
        val sharedDataEdit = sharedData?.edit()
        sharedDataEdit?.putString(key, value)
        sharedDataEdit?.apply()
    }
}