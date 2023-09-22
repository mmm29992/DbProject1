COMPILE = javac -cp bin -d bin uga/cs4370/mydb

run: cl
	javac -cp bin -d bin Main.java
	java -cp bin Main

cl:
	$(COMPILE)/Type.java
	$(COMPILE)/Cell.java
	$(COMPILE)/Predicate.java
	$(COMPILE)/Relation.java
	$(COMPILE)/RelationBuilder.java
	$(COMPILE)/RA.java
	$(COMPILE)/impl/RelationImplementation.java
	$(COMPILE)/impl/RelationBuilderImplementation.java
	$(COMPILE)/impl/RAImplementation.java

test: cl
	javac -cp bin -d bin Tester.java
	java -ea -cp bin Tester

clean:
	rm -r bin