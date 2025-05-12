-- Xóa bảng comments trước vì nó phụ thuộc vào bảng posts và users
DROP TABLE IF EXISTS comments;

-- Xóa bảng posts trước vì nó phụ thuộc vào bảng users
DROP TABLE IF EXISTS posts;

-- Xóa bảng borrowings trước vì nó phụ thuộc vào bảng users và books
DROP TABLE IF EXISTS borrowings;

-- Xóa bảng book_category trước vì nó phụ thuộc vào bảng books và categories
DROP TABLE IF EXISTS book_category;

-- Xóa bảng categories trước
DROP TABLE IF EXISTS categories;

-- Xóa bảng books trước vì nó phụ thuộc vào bảng borrowings và book_category
DROP TABLE IF EXISTS books;

-- Xóa bảng role_function trước vì nó phụ thuộc vào bảng roles và functions
DROP TABLE IF EXISTS role_function;

-- Xóa bảng functions trước
DROP TABLE IF EXISTS functions;

-- Xóa bảng role_user trước vì nó phụ thuộc vào bảng users và roles
DROP TABLE IF EXISTS role_user;

-- Xóa bảng roles trước vì nó phụ thuộc vào bảng role_user
DROP TABLE IF EXISTS roles;

-- Xóa bảng users cuối cùng vì các bảng khác phụ thuộc vào nó
DROP TABLE IF EXISTS users;
