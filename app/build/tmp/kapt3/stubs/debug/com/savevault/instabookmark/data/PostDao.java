package com.savevault.instabookmark.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u000e\u001a\u00020\bH\'J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/savevault/instabookmark/data/PostDao;", "", "getAllPosts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/savevault/instabookmark/data/PostEntity;", "getPostsByTag", "tag", "", "insert", "", "post", "(Lcom/savevault/instabookmark/data/PostEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchPosts", "query", "update", "", "app_debug"})
@androidx.room.Dao()
public abstract interface PostDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.savevault.instabookmark.data.PostEntity post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.savevault.instabookmark.data.PostEntity post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM posts ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.savevault.instabookmark.data.PostEntity>> getAllPosts();
    
    @androidx.room.Query(value = "SELECT * FROM posts WHERE tagsJson LIKE \'%\' || :tag || \'%\' ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.savevault.instabookmark.data.PostEntity>> getPostsByTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tag);
    
    @androidx.room.Query(value = "SELECT * FROM posts WHERE url LIKE \'%\' || :query || \'%\' OR caption LIKE \'%\' || :query || \'%\' OR author LIKE \'%\' || :query || \'%\' OR tagsJson LIKE \'%\' || :query || \'%\' ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.savevault.instabookmark.data.PostEntity>> searchPosts(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
}