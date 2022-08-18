/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Orel Mishan
 * Paddle is the blocks that controlled by the player
 */
public class Paddle implements Sprite, Collidable {
    private int width;
    private Rectangle shape;
    private int speed;
    private biuoop.KeyboardSensor keyboard;
    private List<Ball> balls = new ArrayList<>();

    public static final int PADDLE_HIGHT = 20;
    public static final int PADDLE_Y_START = 555;
    public static final int CHANGE_EAGLE_1 = 300;
    public static final int CHANGE_EAGLE_2 = 330;
    public static final int CHANGE_EAGLE_4 = 30;
    public static final int CHANGE_EAGLE_5 = 60;


    /**
     * constructor.
     *
     * @param gui   to take from him keyBoardSensor
     * @param speed is the speed of the paddle
     * @param width is the width of the paddle
     */
    public Paddle(GUI gui, int speed, int width) {
        this.width = width;
        this.keyboard = gui.getKeyboardSensor();
        this.shape = new Rectangle(new Point(GameFlow.SCREEN_WIDTH / 2 - (width / 2), PADDLE_Y_START),
                width, PADDLE_HIGHT);
        this.speed = speed;
    }

    /**
     * move the paddle left.
     */
    public void moveLeft() {
        for (Ball ball : this.balls) {
            if (ball.getY() > this.shape.getUpperLeft().getY() - ball.getSize()
                    && ball.getY() < this.shape.getUpperLeft().getY() + PADDLE_HIGHT) {
                if (ball.getX() >= this.shape.getUpperLeft().getX() - this.speed
                        && ball.getX() < this.shape.getUpperLeft().getX() + this.width) {
                    return;
                }
            }
        }
        //check if the paddle in the limit of the screen
        if (this.getCollisionRectangle().getUpperLeft().getX() - this.speed <= GameLevel.BORDERS_WIDTH) {
            this.shape = new Rectangle(new Point(GameLevel.BORDERS_WIDTH,
                    this.getCollisionRectangle().getUpperLeft().getY()), this.width, PADDLE_HIGHT);
            return;
        }
        //move the paddle
        this.shape = new Rectangle(new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.speed,
                this.getCollisionRectangle().getUpperLeft().getY()),
                this.getCollisionRectangle().getWidth(), this.getCollisionRectangle().getHeight());
    }

    /**
     * move the paddle right.
     */
    public void moveRight() {

        for (Ball ball : this.balls) {
            if (ball.getY() > this.shape.getUpperLeft().getY() - ball.getSize()
                    && ball.getY() < this.shape.getUpperLeft().getY() + PADDLE_HIGHT) {
                if (ball.getX() <= this.shape.getUpperLeft().getX() + this.speed
                        && ball.getX() > this.shape.getUpperLeft().getX()) {
                    return;
                }
            }
        }

        //check if the paddle in the limit of the screen
        if (this.getCollisionRectangle().getUpperLeft().getX() + this.width + this.speed
                >= GameFlow.SCREEN_WIDTH - GameLevel.BORDERS_WIDTH) {
            this.shape = new Rectangle(new Point(GameFlow.SCREEN_WIDTH - GameLevel.BORDERS_WIDTH - this.width,
                    this.getCollisionRectangle().getUpperLeft().getY()), this.width, PADDLE_HIGHT);
            return;
        }

        //move the paddle
        this.shape = (new Rectangle(new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.speed,
                this.getCollisionRectangle().getUpperLeft().getY()),
                this.getCollisionRectangle().getWidth(), this.getCollisionRectangle().getHeight()));


    }


    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //draw the paddle
        surface.setColor(Color.red);
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        //draw frame to the paddle
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball heatter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball hit the sides of the paddle
        if (this.shape.isAtWalls(collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            //check in which part of the paddle the ball hit
        } else {
            double start = this.getCollisionRectangle().getUpperLeft().getX();
            double length = this.getCollisionRectangle().getWidth() / 5;
            //the ball hit area 1 of the paddle
            if (collisionPoint.getX() >= start && collisionPoint.getX() < start + length) {
                return Velocity.fromAngleAndSpeed(CHANGE_EAGLE_1, GameLevel.BALL_SPEED);
                //the ball hit area 2 of the paddle
            } else if (collisionPoint.getX() >= start + length && collisionPoint.getX() < start + (length * 2)) {
                return Velocity.fromAngleAndSpeed(CHANGE_EAGLE_2, GameLevel.BALL_SPEED);
                //the ball hit area 3 of the paddle
            } else if (collisionPoint.getX() >= start + (length * 2) && collisionPoint.getX() < start + (length * 3)) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                //the ball hit area 4 of the paddle
            } else if (collisionPoint.getX() >= start + (length * 3) && collisionPoint.getX() < start + (length * 4)) {
                return Velocity.fromAngleAndSpeed(CHANGE_EAGLE_4, GameLevel.BALL_SPEED);
                //the ball hit area 5 of the paddle
            } else {
                return Velocity.fromAngleAndSpeed(CHANGE_EAGLE_5, GameLevel.BALL_SPEED);
            }
        }
    }


    /**
     * Add this paddle to the game.
     *
     * @param g is the game
     */

    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @param ball to add to the balls list
     */
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    /**
     * @param ball to remove from the ball list
     */
    public void removeBall(Ball ball) {
        this.balls.remove(ball);
    }

}