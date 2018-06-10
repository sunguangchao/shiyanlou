var Ball = function(game){
  // var image = imageFromPath('ball.png')
  var o = game.imageByName('ball')
  o.x = 50
  o.y = 50
  o.speedX = 5
  o.speedY = 5
  o.fired = false
  o.fire = function(){
    o.fired = true
  }
  o.move = function(){
    log('move')
    if (o.fired) {
      if (o.x < 0 || o.x > 600) {
        o.speedX = -o.speedX
      }
      if (o.y < 0 || o.y > 500) {
        o.speedY = -o.speedY
      }
      //move
      o.x += o.speedX
      o.y += o.speedY
    }
  }
  o.反弹 = function(){
    o.speedY *= -1
  }
  return o
}
