var GuaGame = function(fps, images, runCallback){
  //loads是一个对象，里面是图片的名字，程序会在所有图片载入成功后运行程序
  var g = {
    actions: {},
    keydowns: {},
    images: {},
  }
  var canvas = document.querySelector("#id-canvas")
  var context = canvas.getContext('2d')
  g.canvas = canvas
  g.context = context
  //draw
  g.drawImage = function(guaImage){
    g.context.drawImage(guaImage.image, guaImage.x, guaImage.y)
  }
  //events
  window.addEventListener('keydown', function(event){
    g.keydowns[event.key] = true
  })

  window.addEventListener('keyup', function(event){
    g.keydowns[event.key] = false
  })

  g.registerAction = function(key, callback){
    g.actions[key] = callback
  }
  window.fps = 30
  var runloop = function(){
    log(window.fps)
    //events
    var actions = Object.keys(g.actions)
    for (var i = 0; i < actions.length; i++) {
      var key = actions[i]
      if (g.keydowns[key]) {
        //如果按键被按下，调用注册的 action
        g.actions[key]()
      }
    }
    //update
    g.update()
    //clear
    context.clearRect(0, 0, canvas.width, canvas.height)
    g.draw()
    //调整帧数
    //next run loop
    setTimeout(function(){
      runloop()
    }, 1000/window.fps)
  }
  var loads = []
  //预先载入所有图片
  var names = Object.keys(images)
  for(var i = 0; i < names.length; i++){
    let name = names[i]
    var path = images[name]
    let img = new Image()
    img.src = path
    img.onload = function(){
      //存入g.images中
      g.images[name] = img
      //所有图片都载入成功之后
      loads.push(1)
      log('load images')
      if (loads.length == names.length) {
        g.run()
      }
    }
  }
  g.imageByName = function(name){
    var img = g.images[name]
    var image ={
      w: img.width,
      h: img.height,
      image: img,
    }
    return image
  }
  g.run = function(){
    runCallback(g)
    //timer
    setTimeout(function(){
      runloop()
    }, 1000/fps)
  }

  return g
}
