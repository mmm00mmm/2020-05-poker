# 2020-05-poker
## お題
https://github.com/suzuki-hoge/hoge-works/blob/master/2016-07-poker/README.md

## 日誌
### 2020/5/4
+ 所要時間：6hほど
+ 実装順
  + PokerHandJudge
    + judgeメソッド
    + 役判定privateメソッド（return null固定でTODO）
  + MainでPokerHandJudge.judgeメソッド呼ぶところ
  + SuitとTrumpNumberのEnumたち
    + Enum追加
    + getSuit()とgetTrumpNumber()
  + Enumのテストたち
+ 感想
  + build.gradleのlombok指定間違っていたの今更気づいた
    + 今回はアノテーションを積極的に使う
  + いつも詳細な処理から実装しようとして混乱してたが今回は大枠だけ先に作って整理した
    + 先日のScalaペアプロ（issue自動発行）がとても活きた・・・
    + 頭がこんがらがることが無くてすっきりした
    + 実装イメージを先に考えなきゃいけないのでガワだけ作るまでに結構時間つかった
  + Enumに入出力の値とは別に、役判定する用の数字を持たせるようにした
    + 比較しやすいかなって思ったけど本当に使うかはわからない
    + とりあえずSuitも比較しやすいように数字もたせるようにした
  + Enumのgetほにゃららメソッドたち
    + 一致しなかったらRuntimeException投げることにした、エラーは最低限にして後回し
  + 迷っているところ
    + MainからPokerHandJudgeにStringのListで渡してるけどList<Card>にしたい、Convertはどこでやるべきなんだろう
      + ひとまずあまりこだわるところじゃないので後回し
  + 次は役判定、いまのところまったく思い浮かんでいなから不安。。。。