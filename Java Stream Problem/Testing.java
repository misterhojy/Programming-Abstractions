import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Testing {

    public static class Person implements Comparable<Person>{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.age, o.age);
        }
    }

    public static void main(String[] args) {
//        /* PART 1 */
//        /* Q 1 */
//        List<Integer> list = Arrays.asList(324, 234, 54, 23234, 23412, 4, 3, 4, 24, 23, 42, 35, 43, 6, 5456, 4, 75, 34, -20, 324123, 423, 4, 234, 235);
//        Integer lowest_int = SimpleUtils.least(list, false);
//        System.out.println(lowest_int);
//
//        List<String> list2 = Arrays.asList("B", "C", "D", "A", "E", "a", "b", "z");
//        String lowest_string = SimpleUtils.least(list2, true);
//        System.out.println(lowest_string);
//
//        Person adam = new Person("Adam", 16);
//        Person hojat = new Person("Hojat", 19);
//        Person yama = new Person("Yama", 16);
//        Person mcdonnell = new Person("Mcdonnell", 50);
//        Person shash = new Person("Shash", 16);
//        Person anten = new Person("Anten", 21);
//        List<Person> person_list = Arrays.asList(mcdonnell, adam, hojat, yama, anten, shash);
//        Person lowest_person = SimpleUtils.least(person_list, true);
//        System.out.println(lowest_person);
//
//        /* Q 2 */
//        HashMap<Integer, Boolean> hash_test = new HashMap<>();
//        hash_test.put(1, false);
//        hash_test.put(2, true);
//        hash_test.put(3, false);
//        hash_test.put(4, true);
//        hash_test.put(5, false);
//        List<String> flatten_hash = SimpleUtils.flatten(hash_test);
//        for (String a : flatten_hash) System.out.println(a);
//
//        HashMap<Person, Integer> hash_person = new HashMap<>();
//        hash_person.put(adam, 0301);
//        hash_person.put(hojat, 1144);
//        hash_person.put(yama, 3415);
//        hash_person.put(mcdonnell, 6484);
//        hash_person.put(shash, 9493);
//        List<String> flatten_person = SimpleUtils.flatten(hash_person);
//        for (String a : flatten_person) System.out.println(a);
//
//        /* PART 2 */
//        /* Q 3 / Q 4 */
//        List<Double> numbers = Arrays.asList(-0.5, 2d, 3d, 0d, 4d); // documentation example
//        List<HigherOrderUtils.NamedBiFunction<Double, Double, Double>> operations = Arrays.asList(HigherOrderUtils.add, HigherOrderUtils.multiply, HigherOrderUtils.add, HigherOrderUtils.divide);
//        Double x = HigherOrderUtils.zip(numbers, operations); // expected correct value: 1.125
//        System.out.println(x);
//        // different use case, not with NamedBiFunction objects
//        List<String> strings = Arrays.asList("a", "n", "t");
//        // note the syntax of this lambda expression
//        BiFunction<String, String, String> concat = (s, t) -> s + t;
//        String s = HigherOrderUtils.zip(strings, Arrays.asList(concat, concat)); // expected correct value: "ant"
//        System.out.println(s);
//        //different test
//        List<Integer> numbers_2 = Arrays.asList(1,2,3);
//        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
//        Integer n = HigherOrderUtils.zip(numbers_2, Arrays.asList(sum, sum)); // expected correct value: 6
//        System.out.println(n);
//
//
//        /* PART 3 */
//        /* Q 5 / Q 6 */
//        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
//        System.out.println("SET 1,2,3: BIJECTION");
//        // you have to figure out the data type in the line below
//        Set<Function<Integer,Integer>>  bijections = BijectionGroup.bijectionsOf(a_few);
//        bijections.forEach(aBijection -> {
//            a_few.forEach(b -> System.out.printf("%d --> %d; ", b, aBijection.apply(b)));
//            System.out.println();
//        });
//
//        System.out.println("SET a,b,c,d: BIJECTION");
//        Set<String> a_few2 = Stream.of("a", "b", "c", "d").collect(Collectors.toSet());
//        // you have to figure out the data type in the line below
//        Set<Function<String,String>>  bijections2 = BijectionGroup.bijectionsOf(a_few2);
//        bijections2.forEach(aBijection2 -> {
//            a_few2.forEach(w -> System.out.printf("%s --> %s; ", w, aBijection2.apply(w)));
//            System.out.println();
//        });

        Set<Integer> a_few3 = Stream.of(1, 2, 3, 4).collect(Collectors.toSet());
        System.out.println("SET 1,2,3,4: BIJECTION");
        // you have to figure out the data types in the lines below
        Group<Function<Integer,Integer>> g = BijectionGroup.bijectionGroup(a_few3);
        Set<Function<Integer,Integer>>  bijections = BijectionGroup.bijectionsOf(a_few3);
        bijections.forEach(aBijection -> {
            a_few3.forEach(b -> System.out.printf("%d --> %d; ", b, aBijection.apply(b)));
            System.out.println();
        });
        Function<Integer,Integer> f1 = bijections.stream().skip(22).findFirst().orElse(null);
//        Function<Integer,Integer> f1 = bijections.stream().findFirst().get();
        Function<Integer,Integer> f2 = g.inverseOf(f1);
        Function<Integer,Integer> f3 = g.inverseOf(f2);
        Function<Integer,Integer> id = g.identity();
        Function<Integer,Integer> comp = g.binaryOperation(f1,f2);
        System.out.println("First One: ");
        a_few3.forEach(f -> System.out.printf("%s --> %s; ", f, f1.apply(f)));
        System.out.println();
        System.out.println("f2: inverse of f1: ");
        a_few3.forEach(l -> System.out.printf("%s --> %s; ", l, f2.apply(l)));
        System.out.println();
        System.out.println("f3: inverse of f2: ");
        a_few3.forEach(h -> System.out.printf("%s --> %s; ", h, f3.apply(h)));
        System.out.println();
        System.out.println("id: identity: ");
        a_few3.forEach(i -> System.out.printf("%s --> %s; ", i, id.apply(i)));
        System.out.println();
        System.out.println("comp: Binary OP: ");
        a_few3.forEach(j -> System.out.printf("%s --> %s; ", j, comp.apply(j)));

// Function<T,T> function = in -> ou;
// function.apply(n)

// input.compose(bjection).apply(n)
        // m = bjection.apply(n)
        // input.apply(m)

// domain [1,2,3]

// bijectionsOf 6 Functions

// create an arraylist that repr that identty [1,2,3]
// loop through the bjections (6 functions)
        // create empty list temp_list
        // loop through the domain (n)
        // T val = input.compose(bjection).apply(n)


        // temp_list.add(val)
        // check if the two lists are equal
        // if they are then return the function
        // else move to the next bjection

    }
}