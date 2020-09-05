

$(document).ready(function(){
	$('#spittleForm').submit(function(e){
		e.preventDefault();
		var text = $('#spittleForm').find('textarea[name="text"]').val();
		sendSpittle(text);
	});

	var url = 'http://' + window.location.host + '/SpringBlogShopDevCommunity/spittr';
	var sock = new SockJS(url);
	var stomp = Stomp.over(sock);

  stomp.connect('guest', 'guest', function(frame) {
    console.log('*****  Connected  *****');
    stomp.subscribe("/topic/spittlefeed", handleSpittle);
    stomp.subscribe("/user/queue/notifications", handleNotification);
  });
  
  function handleSpittle(message) {
	  console.log('Spittle:', message);
	  $('#output').append("<b>Received message : " + JSON.parse(message.body).message + "</b><br/>");
  }
 
  function handleNotification(message) {
    console.log('Notification: ', message);
    $('#output').append("<b>Received: " + 
        JSON.parse(message.body).message + "</b><br/>")
  }
  
  function sendSpittle(text) {
    console.log('Sending Spittle');
    stomp.send("/app/spittle", {}, 
        JSON.stringify({ 'text': text }));
  }

  $('#stop').click(function() {sock.close()});
})
  