var loadLevel = function(game, n){
  n = n - 1
  var level = levels[n]
  var blocks = []
  for (var i = 0; i < level.length; i++) {
    var p = level[i]
    var b = Block(game, p)
    blocks.push(b)
  }
  return blocks
}
var blocks = []
var enableDebugMode = function(game, enable){
  if (!enable) {
    return
  }
  window.paused = false
  window.addEventListener('keydown', function(event){
    var k = event.key
    if (k == 'p') {
      window.paused = !window.paused
    }else if ('1234567'.includes(k)){
      blocks = loadLevel(game, Number(k))
    }
  })
  //控制速度
  document.querySelector('#id-input-speed').addEventListener('input', function(event){
    var input = event.target
    log(event, input.value)
    window.fps = Number(input.value)
  })
}
var __main = function(){
  // enableDebugMode(true)
  var images = {
    ball:'ball.png',
    block:'block.png',
    paddle:'paddle.png',
  }
  var score = 0
  //回调函数
  var game = GuaGame(60, images, function(g){
    var paddle = Paddle(game)
    var ball = Ball(game)
    var score = 0
    blocks = loadLevel(game, 1)

    var leftDown = false
    var rigntDown = false
    var paused = false
    game.registerAction('a', function(){
      paddle.moveLeft()
    })
    game.registerAction('d', function(){
      paddle.moveRight()
    })
    game.registerAction('f', function(){
      ball.fire()
    })

    game.update = function(){
      if(window.paused){
        return
      }
      ball.move()
      //判断相撞
      if (paddle.collide(ball)) {
        //这里应该调用一个 ball.反弹() 来实现
        ball.反弹()
      }
      //判断ball和block相撞
      for (var i = 0; i < blocks.length; i++) {
        var block = blocks[i]
        if (block.collide(ball)) {
          log('block 相撞')
          block.kill()
          ball.反弹()
          //更新分数
          score += 100
        }
      }
    }
    game.draw = function(){
      // game.context.drawImage(paddle.image, paddle.x, paddle.y)
      game.drawImage(paddle)
      game.drawImage(ball)
      //draw block
      for (var i = 0; i < blocks.length; i++) {
        var block = blocks[i]

        if (block.alive) {
          game.drawImage(block)
        }
      }
      //draw lables
      game.context.fillText('分数：'+score, 10, 490)
    }
  })
  enableDebugMode(game, true)

}
__main()
