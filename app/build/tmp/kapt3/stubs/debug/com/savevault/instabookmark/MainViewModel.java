package com.savevault.instabookmark;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007J\u000e\u0010\'\u001a\u00020#2\u0006\u0010(\u001a\u00020\bJ\u000e\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000bJ\u000e\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020\u000bJ\u000e\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020\u000bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0016\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n \u0019*\u0004\u0018\u00010\u001d0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010\u00a8\u0006/"}, d2 = {"Lcom/savevault/instabookmark/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_allPosts", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/savevault/instabookmark/data/PostEntity;", "_searchQuery", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_selectedTags", "", "allPosts", "getAllPosts", "()Lkotlinx/coroutines/flow/StateFlow;", "allTags", "getAllTags", "db", "Lcom/savevault/instabookmark/data/AppDatabase;", "filteredPosts", "getFilteredPosts", "oembedService", "Lcom/savevault/instabookmark/network/InstagramOEmbedService;", "kotlin.jvm.PlatformType", "postDao", "Lcom/savevault/instabookmark/data/PostDao;", "retrofit", "Lretrofit2/Retrofit;", "searchQuery", "getSearchQuery", "selectedTags", "getSelectedTags", "addTags", "", "postId", "", "newTags", "deletePost", "post", "savePostFromUrl", "url", "setSearchQuery", "query", "toggleTagSelection", "tag", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.savevault.instabookmark.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.savevault.instabookmark.data.PostDao postDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.savevault.instabookmark.data.PostEntity>> _allPosts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.savevault.instabookmark.data.PostEntity>> allPosts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _selectedTags = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> selectedTags = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.savevault.instabookmark.data.PostEntity>> filteredPosts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> allTags = null;
    private final retrofit2.Retrofit retrofit = null;
    private final com.savevault.instabookmark.network.InstagramOEmbedService oembedService = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.savevault.instabookmark.data.PostEntity>> getAllPosts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> getSelectedTags() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.savevault.instabookmark.data.PostEntity>> getFilteredPosts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> getAllTags() {
        return null;
    }
    
    public final void savePostFromUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void addTags(long postId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> newTags) {
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void toggleTagSelection(@org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
    }
    
    public final void deletePost(@org.jetbrains.annotations.NotNull()
    com.savevault.instabookmark.data.PostEntity post) {
    }
}