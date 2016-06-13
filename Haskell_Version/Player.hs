module Player
(
  Player(..)
  ,move
  ,hit
  ,drink
  ,getPotion
) where

data Player = Player{hp :: Int, potions :: Int} deriving (Show,Eq)

move :: Player -> Player
move player  = Player ((hp player) - 5) (potions player)

hit :: Player -> Player
hit player  = Player ((hp player) - 20) (potions player)

drink :: Player -> Player
drink player  = Player ((hp player) + 20) ((potions player) - 1 )

getPotion :: Player -> Player
getPotion player  = Player (hp player) ((potions player) + 1)



