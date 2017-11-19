package oop.file.finder;

import java.util.function.Consumer;

public interface IEnumerator<T> {

	T current();

	boolean moveNext();

	void reset();
	
	default void forEach(Consumer<T> consumer) {
		reset();

		while (moveNext()) {
			consumer.accept(current());
		}
	}
}
