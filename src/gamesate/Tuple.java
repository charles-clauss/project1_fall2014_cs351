package gamesate;

/**
 * Generic methods for tuples, mostly used as a 
 * data structure for generating and updating the 
 * vertices in the game graph.
 * based on implementation of our textbook's generic
 * tuple approach, unsure why this isn't working
 */
public class Tuple {
	public static <A,B> TwoTuple<A,B> tuple(A a, B b){
		return new TwoTuple<A,B>(a,b);
	}

	public static <A,B,C> ThreeTuple<A,B,C> tuple(A a, B b, C c){
		return new ThreeTuple<A,B,C>(a,b,c);
	}

	public static <A,B,C,D> FourTuple<A,B,C,D> tuple (A a, B b, C c, D d){
		return new FourTuple<A,B,C,D>(a,b,c,d);
	}

	public static <A,B,C,D,E> FiveTuple<A,B,C,D> tuple (A a, B b, C c, D d, E e){
		return new FiveTuple<A,B>(a,b);
	}

	public static <A,B,C,D,E> SixTuple<A,B,C,D,E> tuple(A a, B b, C c, D d, E e){
		return new SixTuple<A,B>(a,b);
	}
}
