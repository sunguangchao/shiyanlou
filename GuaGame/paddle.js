var Paddle = function(){
  var image = imageFromPath('paddle.png')
  var o = {
    image: image,
    x: 100,
    y: 300,
    //自己加的，没有效果
    w: 376,
    h: 126,
    speed: 15,
  }
  var paddle = o
  o.move = function(x){
    if (x < 0) {
      x = 0
    }
    if (x > 600 - o.image.width) {
      x = 600 - o.image.width
    }
    o.x = x
  }
  o.moveLeft = function(){
    //
    o.move(paddle.x - paddle.speed)
  }

  o.moveRight = function(){
    o.move(paddle.x + paddle.speed)
  }
  o.collide = function(ball){
    // if (ball.y + ball.image.height > o.y) {
    //   if (ball.x > o.x && ball.x < o.x + o.image.width) {
    //     log('相撞')
    //     return true
    //   }
    // }
    // return false
    return rectIntersects(o,ball) || rectIntersects(ball, o)
  }
  return o
}
