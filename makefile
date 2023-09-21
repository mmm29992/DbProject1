COMPILE = javac -cp bin -d bin uga/cs4370/mydb

run:
	$(COMPILE)/Type.java
	$(COMPILE)/Cell.java
	$(COMPILE)/Predicate.java
	$(COMPILE)/Relation.java
	$(COMPILE)/RelationBuilder.java
	$(COMPILE)/RA.java
	$(COMPILE)/impl/RelationImplementation.java
	$(COMPILE)/impl/RelationBuilderImplementation.java
	javac -cp bin -d bin Main.java
	java -cp bin Main

clean:
	rm -r bin