# MyRestAPI

<div class="row">
		<div class="col-lg-10">
			<h1 class="page-header" id="howto">How to</h1>
		</div>
	</div>
  <div class="row">
		<div class="col-lg-12">
				<p>To get a Git project into your build:</p>
		</div>
	</div>
  <p>
					<b>Step 1.</b> Add the JitPack repository to your build file
				</p>
 <div class="tab-content">
					      <div role="tabpanel" class="tab-pane active" id="gradle">
                              <p>Add it in your root build.gradle at the end of repositories:</p>
                            <pre class="kode language-css code-toolbar"><code class=" kode language-css">	<span class="token selector">allprojects</span> <span class="token punctuation">{</span>
		<span class="token selector">repositories</span> <span class="token punctuation">{</span>
			<span class="token selector">...
			maven</span> <span class="token punctuation">{</span> url <span class="token string">'https://jitpack.io'</span> <span class="token punctuation">}</span>
		<span class="token punctuation">}</span>
	<span class="token punctuation">}</span></code></pre>
  </div>
  </div>
   <p><b>Step 2.</b> Add the dependency</p>
                        <div class="tab-content">
					      <div role="tabpanel" class="tab-pane active" id="gradle">
                   <code id="depCodeGradle" class=" kode  language-css">	<span class="token selector">dependencies</span> <span class="token punctuation">{</span>
	        implementation <span class="token string">'com.github.koushik100:MyRestAPI:-SNAPSHOT'</span>
	<span class="token punctuation">}</span>
</code>
                   
                  
                        
						

						
					

                              

					
