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
   撰寫一個 Client 及 Server 程式達成以下的功能

            Server 執行後會等待 Client 傳來訊息
            Client從命令列輸入一個大於0 的整數並將這一個整數減一傳給 Server 發起訊息傳送的動作
            Server/Client 收到對方所傳來的整數後，將收到的數值減一並回傳給對方
            雙方重覆執行動作(3)直到收到的數值為 0
            當收到 0 這一個數的一方結束連線
            Server/Client 需印出過程中所收到整數的值
            
## Unit 3-Application based on TCP
   修改範例 SimpleMailClient.java，寫一個 Mail Client達成以下的功能
   
            秀出 mailbox 中有幾封 mail
            刪除第 nn 封信
            列出信箱中每一封 mail 的寄信時間、信件主題、寄件人、收件人
            列出第 nn 封信的內容 (如信件已編碼需解碼)
            
## Unit 4-Concurrent Processes
   修改 [__Lab 2__](https://github.com/Pinganan/Junior_NetWorking/blob/main/README.md#unit-2-stream-and-client-server-model) 的 Server 程式，當 Server 收到 Client 的連線建立訊息後即產生一個新的 thread 與 Client 繼續溝通
   
            Server 執行後會等待 Client 傳來訊息
            Client 從命令列輸入一個大於0 的整數並將這一個整數減一傳給 Server 發起訊息傳送的動作
            Server/Client 收到對方所傳來的整數後，將收到的數值減一並回傳給對方
            雙方重覆執行動作(3)直到收到的數值為 0
            當收到 0 這一個數的一方結束連線
            Server/Client 需印出過程中所收到整數的值

## Unit 5 - UDP

   ### week1
      Wireshark 封包補捉軟體，補捉 UDPClient1 / UDPServer1 這一個範例的執行過程所傳送的封包。寫下 Client 與 Server 之間傳送的所有封包

               是否有連線建立的封包?
               那些封包為 Client 送訊息給 Server的封包
               那些封包為 Server 送訊息給 Client的封包
               是否有連線結束的封包
            
            
