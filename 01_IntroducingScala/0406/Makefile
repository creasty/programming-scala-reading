OBJS := 04_shapes.scala 05_shapes_actor.scala
MAIN := 06_shapes_actor_script.scala

package: $(OBJS)
	scalac $(OBJS) 2> /dev/null

run:
	scala -cp . $(MAIN)
