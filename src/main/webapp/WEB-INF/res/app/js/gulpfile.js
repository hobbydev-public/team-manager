var gulp = require('gulp');
var rename = require('gulp-rename');
var uglify = require('gulp-uglify');

var jsFiles = 'dist/app.bundle.js',
    jsDest = 'dist/';

gulp.task('minify', function() {
    return gulp.src(jsFiles)
        .pipe(rename('bundle.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest(jsDest));
});