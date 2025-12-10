INSERT INTO tag (tagName)
VALUES ('tag1');
INSERT INTO tag (tagName)
VALUES ('tag2');
INSERT INTO tag (tagName)
VALUES ('tag3');

INSERT INTO word (word, pronounce, haveTag)
VALUES ('不可能', 'ふかのう', '1,2');
INSERT INTO word (word, pronounce, haveTag)
VALUES ('右', 'みぎ', '2,3');

INSERT INTO content (wordId, haveTag, content)
VALUES (1, '1', '世界で一番辞書に掲載されているべき単語');
INSERT INTO content (wordId, haveTag, content)
VALUES (1, '2', '時間と人材と資金が無限にあるなら存在しないもの
裏を返せば実現できない理由はこれのどれかが不足していること');
INSERT INTO content (wordId, haveTag, content)
VALUES (2, '3', '舟を編むの帯に書いてあったやつ
簡単なのに一意に定まる説明は難しい単語');
INSERT INTO content (wordId, haveTag, content)
VALUES (2, '2', '時計の12時の方向を向いているときの3時の方向
また、概ね上記の方向のこと');
