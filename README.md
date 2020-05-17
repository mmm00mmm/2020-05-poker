# 2020-05-poker
## お題
https://github.com/suzuki-hoge/hoge-works/blob/master/2016-07-poker/README.md

## 日誌
### 2020/5/13-5/17
#### 指摘反映
+ [x] ︎onePairの中身を共通化する
+ [x] ストレート微妙。forで抜けなかったらこっち、はバグる（for elseということもある）
+ [x] フラッシュももう少し楽にかける
+ [x] RSFいまいち
+ [x] sfのテストはもう１パタン欲しい
+ [x] enumにStringもつとしてもjudgeの終わった段階でstringにするメリットなし、Mainでやるべき。出力要件はドメインではない
+ [x] 各判定メソッドはpackage privateがいい
+ [x] テスト側にString->CardListのメソッドを作る。毎回CardList生成が長い
+ [x] return true はやめるべき
+ [x] judgeの戻りがStringである必要がない
+ groovyのlistは[]でいい
+ CardListが薄い
  + パース失敗を例外にするなら、例えば同じカードがダブっているとかも例外にしたほうがよい
  + [x] 枚数も順序も伝わってこない
  + [x] List<Card>は無い！
+ enumにStringもつな
+ Convertは使ってないなら消す！

#### メモ
+ Straight判定について
  + 隣の値との関係は意識しない
  + どういうリストであるべきかを考える
  + ストレートのリストを作り、そのリストと比較すればよい
  + ストレートのリストの作成方法はいろんなものがある（下記参照）<br>
  （zipについては：https://qiita.com/suzuki-hoge/items/b095f218d9b3d7f1e48c）
 ```
  1. IntStream.range(n, n + 5)
  2. Stream.genarate(() -> 1).limit(5) // 1を5つ作成する
  3. Stream.iterate(n, n -> n + 1).limit(5) // 初期値を1ずつ増やした数を5つ作成する
  4. ストレートの比較を[0, 1, 2, 3, 4]で行う
     List<Integer> ns = Arrays.asList(6, 7, 8, 9, 10);
     ns.stream()
             .map(n -> n - 6)
             .collect(Collectors.toList())
             .equals(
                     Arrays.asList(0, 1, 2, 3, 4)
             );
  
  5. javaslangだと以下のような書き方も可能
     ns = List.range(6,11); // [6, 7, 8, 9, 10]
     // Taple形式で、(6,7)(7,8)(8,9)(9,10)を作る
     // zipは小さいリストの方に合わせて作られる
     ns.zip(ns.tail()).map(t -> t._2 - t._1).forAll(n -> n == 1) // tail: [7, 8, 9, 10]
  
 ```
+ PokerHandJudgeはそのままで、Cardsを定義してもよい
+ Cardsに判定を置く場合、各メソッドの引数でCardsをもらう必要はない
+ ConvertはCardsのFactory、xのファクトリーがxを返すべき<br>
今回だとList<Card>を返しているのが変
+ staticとprivateはセットで扱う<br>
staticでコンストラクタを使いたいなら、コンストラクタはprivateにして他からnewできないようにするべき<br>
`@AllArgsConstructor(access = AccessLevel.PRIVATE)`

### 2020/05/10
+ 所要時間：2.5h
+ 実装順
  + 役判定メソッドをpublicへ修正
  + publicメソッドのテスト追加
  + Convertクラス作成（ただし使用していない）
+ 感想
  + Convertクラス作ってCardListをCardsに修正したりしてみたけどうまくいかない
    + Convertクラスで入力文字(String)→List<Card>にしたあと、そのままCardsに渡せばいいのかな
    + PokerHandJudgeの処理をCardsに移動すべき？
    + いつもみたく頭がこんがらがったので、中断
  + privateからpublicにしたことで各テストがやりやすくなった。
  + 今回はここらへんまでで区切る。２回目でConvertクラスとCardsクラスを使いたい。
### 2020/05/06
+ 所要時間：4hほど
+ 実装順
  + テストパタン追加
  + リファクタ
+ 感想
  + テストが書けない( ◠‿◠ )
    + ありえない組み合わせのテストパタン作っちゃった
    + めちゃくちゃテストしにくい・・・・
  + CardListなくせない・Card側の"-"の変換なくせてない
  + コード量減らせてない
    + 似たような処理はprivateメソッド切った（sortとgroupby）
    + PokerHandJudgeクラスにsortとgroupbyを置くのは違和感ある
### 2020/5/5
+ 所要時間：5/4と同じく6hほど
+ 実装順
  + PokerHandJudge.judgeメソッドの各判定
+ 感想
  + ストレートの実装が難しかった
    + 連続した数字をどう判定するかが難しかった
    + 最初、引数でもらったカードをsortして、最初の数字から連続した数の配列つくって、それと比較するようにした
    + 変数たくさん増えたので整理して今の形になった
  + フルハウスもどう判定したらいいかわからなかったが、ワンペアとスリーカードの判定を呼んじゃえばいいと気づいた
  + ロイヤルストレートフラッシュはA-K-Q-J-10の配列作って比較するようにした
    + フラッシュの判定も必要だったのでif文でなんとかしてる。けどreturn箇所多いのでバグりそうな可能性が一番高い
+ 次回：テストパタン追加と追加仕様の実装
    

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