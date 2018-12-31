# Mobile_Coding_Challenge
## Libraries
### RecyclerView 
is a ViewGroup that is prepared to render any adapter-based view in a similar way.
It is supposed to be the successor of ListView and GridView.
It needs to have a layout manager and an adapter to be instantiated. And i used a LinearLayoutManager that's shows items in a vertical or horizontal scrolling list.
```
dependencies {
    implementation 'com.android.support:recyclerview-v7:27.1.1'
}
```
### CardView 
Is a FrameLayout with a rounded corner background and shadow. And it wraps a layout and will often be the container used in a layout for each item within a ListView or RecyclerView.
```
dependencies {
    implementation 'com.android.support:cardview-v7:27.1.1'
}
```
### Picasso 
Displaying images is easiest using Picasso from Square which will download and cache remote images.
```
dependencies {
    implementation 'com.squareup.picasso:picasso:2.71828'
}
```
### Gson 
Google's Gson library provides a powerful framework for converting between JSON strings and Java objects. 
And it can be used with any networking library.
```
dependencies {
    implementation 'com.google.code.gson:gson:2.8.5'
}
```
