var Block = function(position){
  var p = position
  var image = imageFromPath('block.png')
  var o = {
    image: image,
    x: p[0],
    y: p[1],
    w: 50,
    h: 20,
    alive: true,
  }
  o.kill = function(){
    o.alive = false
  }
  //检测相撞
  o.collide = function(b){
      return o.alive && (rectIntersects(o,b) || rectIntersects(b, o))
  }
  return o
}
