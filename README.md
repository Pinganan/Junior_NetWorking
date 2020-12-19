# Junior_NetWorking

## Unit 1-Introduction

   Wireshark補捉SimpleServer.class / SimpleClient.class執行過程所傳送的封包，寫下Client與Server之間傳送的所有封包

            請問 Server 所開的 Port number 為？
            請問 Client 所使用的 Port number 為？
            請貼上所補捉封包的畫面。
            那些封包屬於 TCP 連線建立的封包，這些封包的 Flag 值為何?
            那些封包為 Client 送訊息給 Server 及 Server 回應收到訊息的封包?
            那些封包為 Server 送訊息給 Client 及 Client 回應收到訊息的封包?
            那些封包為 TCP 連線結束的封包？
## Unit 2-Stream and Client Server Model

   撰寫一個 Client 及 Server 程式

            Server 執行後會等待 Client 傳來訊息
            Client從命令列輸入一個大於0 的整數並將這一個整數減一傳給 Server 發起訊息傳送的動作
            Server/Client 收到對方所傳來的整數後，將收到的數值減一並回傳給對方
            雙方重覆執行動作(3)直到收到的數值為 0
            當收到 0 這一個數的一方結束連線
            Server/Client 需印出過程中所收到整數的值
