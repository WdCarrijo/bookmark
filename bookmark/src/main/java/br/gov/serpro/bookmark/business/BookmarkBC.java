package br.gov.serpro.bookmark.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.bookmark.domain.Bookmark;
import br.gov.serpro.bookmark.message.InfoMessages;
import br.gov.serpro.bookmark.persistence.BookmarkDAO;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	MessageContext messageContext;

	@Override
	public Bookmark insert(Bookmark bookmark) {
		messageContext.add(InfoMessages.BOOKMARK_INSERT_OK.getText(),bookmark.getDescription());
		return super.insert(bookmark);
	}
	
	@Override
	public Bookmark update(Bookmark bookmark) {
		messageContext.add(InfoMessages.BOOKMARK_UPDATE_OK.getText(), bookmark.getDescription());
		return super.update(bookmark);
	}
	
	@Override
	public void delete(Long id) {	
		super.delete(id);
		messageContext.add(InfoMessages.BOOKMARK_DELETE_OK.getText(), id);
	}
	

	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new Bookmark("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Blog", "http://blog.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("SVN", "http://svn.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Maven", "http://repository.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));
		}
	}

}
