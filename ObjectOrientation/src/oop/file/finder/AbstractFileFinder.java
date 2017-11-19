package oop.file.finder;

import oop.javabean.Config;
import oop.my.backup.candidate.Candidate;

public abstract class AbstractFileFinder implements FileFinder<Candidate> {

	protected Config config;
	protected String[] files;
	protected Integer index = -1;

	protected AbstractFileFinder() {

	}

	protected AbstractFileFinder(Config config) {
		if (config != null) {
			this.config = config;
		} else {
			System.out.println("AbstractFilfFinder initial failed !!");
		}
	}

	@Override
	public Candidate current() {
		return createCandidate(this.files[this.index]);
	}

	@Override
	public boolean moveNext() {
		return ++this.index < this.files.length;
	}

	@Override
	public IEnumerator<Candidate> getEnumerator() {
		return this;
	}

	@Override
	public void reset() {
		this.index = -1;
	}

	protected abstract Candidate createCandidate(String string);
}
