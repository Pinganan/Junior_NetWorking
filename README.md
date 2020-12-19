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
   
   - Wireshark 封包補捉軟體，補捉 UDPClient1 / UDPServer1 這一個範例的執行過程所傳送的封包。寫下 Client 與 Server 之間傳送的所有封包

            是否有連線建立的封包?
            那些封包為 Client 送訊息給 Server的封包
            那些封包為 Server 送訊息給 Client的封包
            是否有連線結束的封包
   - 寫一個 UDP Server 接收 Client 傳來的訊息，Client 將會傳來一個整數的訊息，當 Server 收到這一個整數後印出 Client 的 IP/port 及收到的值，並將該整數值減一回傳給 Client。接著 Server 繼續等待下一個訊息
   
            Client 從命令列輸入一個大於0 的整數 n 並將這一個整數傳給 Server 發起訊息傳送的動作並設定一個 timeout 時間 0.01 second
            Client 在 timeout 後如果沒有收到 Server 回傳的訊息，將傳送值重設為 n 再傳給 Server
            Client 在 timeout 前收到 Server 回傳的值，印出該值，如果該值為0 則結束 Client 程式，否則直接將該值再傳送給 Server
            
   ### week2
   
            解決 UDP 封包在傳送過程中會遺失的問題，SAWSocket.java 實作了 Stop-and-wait 的 Flow control 機制。但我們知道 Stop-and wait 的效率不佳，所以請修改 SAWSocket.java 及UDPServer3.java 程式，讓我們可以從 UDPServer3 的命令列參數中填入 Sliding windows 的值 w，讓送方可以連續送出 w 個封包，收方才回一個 ACK。請注意，收方仍要有 timeout 的機制以解決送方沒有送滿w 個封包，而收方一直不回 ACK 的問題。
            
## Unit 6-Remote Method Invocation
   
   ### week1
   
   - 這一份作業請大家以 Java RMI 撰寫一個討論區。這一個討論區的運作分為 Client 端與 Server 端程式，其主要功能如下：

      - 使用者註冊：每一個使用者要新增討論主題或回覆前皆需先申請一帳號，所有的討論主題及回覆內容都會顯示使用者名稱。
      - Client 端：提供一個介面讓使用者可以 (1) 註冊 (2) 新增討論主題 (3) 列出討論主題名稱 (4) 回覆討論議題 (5) 顯示一討論主題的內容及其所有回覆內容 (6) 刪除討論主題。
            - 註冊時要確認使用者名稱是否重覆；請使用 register() 這一個 API 向 Server 註冊使用者。
            - 新增討論主題時需分別輸入 (a) 討論主題名稱 及 (b) 討論主題內容；請使用 create() 這一個 API 向 Server 新增討論主題。(PS: 記得要傳給 Server 端使用者名稱。)
            - 使用 subject() 這一個 API 向 Server 查詢所有討論主題名稱。
            - 使用者可以針對某一討論主題回覆其意見；請使用 reply() 這一個 API 向 Server 新增回覆內容。(PS: 記得要傳給 Server 端使用者名稱。)
            - 使用 discussion()這一個 API 向 Server 查詢一討論主題的內容及其所有回覆內容。(PS: 記得要顯示發言人的名稱)
            - 如果一討論主題尚未有回覆或一回覆之後無其它回覆，發言人可使用 delete() API 刪除其討論主題或回覆。
      - Server 端：接收 Client 端送來的資訊並將這些資訊儲存在 Server 端（請自行設計儲存格式）。Server 端同時提供 register(), create(), subject(), reply(), discussion() 及 delete() 等 API 供 Client 端程式呼叫以處理資訊。Server 端的程式需滿足以下的要求。
      允許多個 Client 同時存取 Server 端的資訊，Server 程式必須處理臨界區間 (Critical Section) 的問題。
      Server 端接收到討論主題或回覆時需同時儲存日期及時間。
      請自行定義一個 Error 回傳值，當所要求的資訊不存在時回傳 Error。
      你的程式請分成 Interface, RMI Service Implementation, Server Program 與 Client Program 等四個檔案，並將這四個檔案以 ZIP 壓縮後上傳 iLearn (請以學號為檔名)。
