

	$(document).ready(function(){
		$('#blogForm').submit(function(e){
    		e.preventDefault();
    		var text = $('#blogForm').find('textarea[name="text"]').val();
    		sendSpittle(text);
    	});
    
		var url = 'http://' + window.location.host + '/SpringBlogShopDevCommunity/spittr';
    	
      var sock = new SockJS(url);
      var stomp = Stomp.over(sock);

      stomp.connect('guest', 'guest', function(frame) {
        console.log('*****  Connected  *****');
        stomp.subscribe("/topic/hit", handleSpittle);
        stomp.subscribe("/user/queue/notifications", handleNotification);
      });
      
      function handleSpittle(message) {
    	  console.log('Spittle:', message);
    	  $('#output').append("<b>Received spittle: " + JSON.parse(message.body).message + "</b><br/>");
      }
      
      function handleNotification(message) {
        console.log('Notification: ', message);
        $('#output').append("<b>Received: " + 
            JSON.parse(message.body).message + "</b><br/>")
      }
      
      function sendSpittle(text) {
        console.log('Sending Blog');
        stomp.send("/app/hit", {}, 
            JSON.stringify({ 'text': text }));
      }
      

      $('#stop').click(function() {sock.close()});
	})
      