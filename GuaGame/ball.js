var Ball = function(){
  var image = imageFromPath('ball.png')
  var o = {
    image: image,
    x: 50,
    y: 50,
    speedX: 5,
    speedY: 5,
    fired: false
  }
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
